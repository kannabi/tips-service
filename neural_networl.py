import json
import numpy as np
import tensorflow as tf
from sklearn.preprocessing import normalize
from sklearn.model_selection import train_test_split
from keras.models import Model, load_model
from keras.layers import Input, Dense
from keras.callbacks import ModelCheckpoint, TensorBoard
from keras import regularizers

class Antifraud():
    def __init__():
        self.correct_shape = (6,)
        self.X_train = np.zeros(shape=(6,))
        self.y_train = np.zeros(shape=1)
        self.clf = Autoencoder()

    def read_data(self, filepath='test.json'):
        with open(filepath) as f:
            X = json.load(f)
        X = np.array(list(X.values()))
        X = X / np.linalg.norm(X)
        if X.shape != self.correct_shape:
            print('Incorrect shape!')
            return None
        return X

    def predict(self, X):
        return self.clf.predict(X)

    def update_data(self, y=None):
        if not y:
            y = np.ones(shape=len(X))
        self.X_train = np.vstack((self.X_train, X))
        self.y_train = np.vstack((self.y_train, y))
        self.clf.fit([elem for elem in self.X_train if self.y_train[self.X_train.index(elem)] != 0])

    def zalupa(self):
        self.update_data()
        answer = self.predict(self.read_data())

        
class Autoencoder():
    def __init__(self, threshold=0.0004,  nb_epoch=2, encoding_dim=3, batch_size=2):
        self._input_dim = 0
        self._encoding_dim = encoding_dim
        self._nb_epoch = nb_epoch
        self._batch_size = batch_size
        self._threshold = threshold

    def fit(self, X):
        X_train, X_test = train_test_split(X, test_size=0.1)
        self._input_dim = X_train.shape[1]
        input_layer = Input(shape=(self._input_dim, ))
        encoder = Dense(self._encoding_dim, activation='tanh',
                        activity_regularizer=regularizers.activity_l1(10e-5))(input_layer)
        encoder = Dense(int(self._encoding_dim / 2), activation='relu')(encoder)
        decoder = Dense(int(self._encoding_dim / 2), activation='tanh')(encoder)
        decoder = Dense(self._input_dim, activation='relu')(decoder)
        autoencoder = Model(input=input_layer, output=decoder)
        autoencoder.compile(optimizer='adam',
                            loss='mean_squared_error',
                            metrics=['accuracy'])
        history = autoencoder.fit(X_train, X_train,
                            nb_epoch=self._nb_epoch,
                            batch_size=self._batch_size,
                            shuffle=True,
                            validation_data=(X_test, X_test),
                            verbose=0).history
        self.autoencoder = autoencoder

    def predict(self, X_test):
        predictions = self.autoencoder.predict(X_test)
        mse = np.mean(np.power(X_test - predictions, 2), axis=1)
