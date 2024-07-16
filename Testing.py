import numpy as np
import tensorflow as tf
from sklearn.model_selection import train_test_split
from sklearn.metrics import confusion_matrix, accuracy_score
import matplotlib.pyplot as plt

# Load data from two .mat files
# (Replace this with your actual data loading code)
data_u = np.random.rand(100, 10)
data_v = np.random.rand(100, 10)
labels = np.concatenate([np.zeros(100), np.ones(100)])

# Concatenate data
features = np.concatenate([data_u, data_v], axis=0)

# Shuffle data
indices = np.arange(len(labels))
np.random.shuffle(indices)
features = features[indices]
labels = labels[indices]

# Split data
X_train, X_test, y_train, y_test = train_test_split(features, labels, test_size=0.2, random_state=42)

# Build and train a simple neural network
model = tf.keras.Sequential([
    tf.keras.layers.Dense(64, activation='relu', input_shape=(features.shape[1],)),
    tf.keras.layers.Dense(1, activation='sigmoid')
])

model.compile(optimizer='adam', loss='binary_crossentropy', metrics=['accuracy'])
history = model.fit(X_train, y_train, epochs=10, validation_data=(X_test, y_test))

# Predict using test data
predictions = model.predict(X_test)
binary_predictions = np.round(predictions)

# Plot train/test loss history
plt.plot(history.history['loss'], label='Training Loss')
plt.plot(history.history['val_loss'], label='Validation Loss')
plt.xlabel('Epoch')
plt.ylabel('Loss')
plt.legend()
plt.show()

# Confusion matrix
conf_mat = confusion_matrix(y_test, binary_predictions)
print('Confusion Matrix:')
print(conf_mat)

# Accuracy
acc = accuracy_score(y_test, binary_predictions)
print(f'Accuracy: {acc}')
