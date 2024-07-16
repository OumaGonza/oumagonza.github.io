#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Step 1: Data Loading and Processing
import numpy as np
import scipy.io
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler

# Load data
data_u = scipy.io.loadmat('/home/katana/Kanairo/Mathematics/All_U_Micro.mat')
data_v = scipy.io.loadmat('All_V05_Micro.mat')

# Process data
sampled_data = np.concatenate([data_u['your_feature_key'], data_v['your_feature_key']], axis=0)
target = np.concatenate([data_u['your_target_key'], data_v['your_target_key']], axis=0)

# Shuffle data
indices = np.arange(sampled_data.shape[0])
np.random.shuffle(indices)
sampled_data = sampled_data[indices]
target = target[indices]

# Step 2: Data Splitting
X_train, X_test, y_train, y_test = train_test_split(sampled_data, target, test_size=0.2, random_state=42)

# Step 3: Model Training and Prediction (using TensorFlow and Keras)
import tensorflow as tf
from tensorflow.keras import Sequential, layers

# Define the model
model = Sequential([
    layers.Dense(64, activation='relu', input_shape=(input_shape,)),
    layers.Dense(32, activation='relu'),
    layers.Dense(num_classes, activation='softmax')
])

# Compile the model
model.compile(optimizer='adam', loss='sparse_categorical_crossentropy', metrics=['accuracy'])

# Train the model
history = model.fit(X_train, y_train, epochs=10, validation_data=(X_test, y_test))

# Predict using the test data
y_pred = model.predict(X_test)

# Step 4: Evaluation
# Plot loss history
import matplotlib.pyplot as plt

plt.plot(history.history['loss'], label='train_loss')
plt.plot(history.history['val_loss'], label='val_loss')
plt.legend()
plt.show()

# Confusion Matrix
from sklearn.metrics import confusion_matrix

conf_matrix = confusion_matrix(y_test, np.argmax(y_pred, axis=1))
print(conf_matrix)
