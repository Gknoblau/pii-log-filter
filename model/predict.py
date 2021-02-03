import json
import pickle
from collections import OrderedDict

from flask import Flask, request
from keras.models import load_model
from keras.preprocessing import sequence

app = Flask(__name__)
max_log_length = 1024

with open(('tokenizer.pkl'), 'rb') as handle:
    tokenizer = pickle.load(handle)


@app.route('/predict', methods = ['POST'])
def predict():
    model = load_model('privapi-lstm-model.h5')
    model.load_weights('privapi-lstm-weights.h5')
    model.compile(loss='binary_crossentropy', optimizer='adam', metrics=['accuracy'])

    reqd = json.loads(request.data.decode("utf-8"), object_pairs_hook=OrderedDict)
    reqj = json.dumps(reqd, separators=(',', ':'))
    reqs = tokenizer.texts_to_sequences([reqj])
    reqsp = sequence.pad_sequences(reqs, maxlen=max_log_length)
    prediction = model.predict(reqsp)
    prediction_class = model.predict_classes(reqsp)
    return {
        "is_sensitive": False if prediction_class[0][0] == 0 else True,
        "probability": float(prediction[0][0])
    }