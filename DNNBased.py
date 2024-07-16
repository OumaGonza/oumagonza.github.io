import h5py
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
import tensorflow as tf
from tensorflow.keras import Sequential, layers
import matplotlib.pyplot as plt
from sklearn.metrics import confusion_matrix, accuracy_score
from sklearn.ensemble import RandomForestClassifier
import shap
from sklearn.cluster import KMeans
from sklearn.metrics import silhouette_samples, silhouette_score
import matplotlib.cm as cm

# Load data using h5py for MATLAB v7.3 files
with h5py.File('/home/katana/Kanairo/Mathematics/All_U_Micro.mat', 'r') as file:
    # Access the variables in the MATLAB file
    your_feature_key_u = file['your_feature_key_u'][:]
    your_target_key_u = file['your_target_key_u'][:]

# Repeat the same for the other file ('All_V05_Micro.mat')
with h5py.File('/home/katana/Kanairo/Mathematics/All_V05_Micro.mat', 'r') as file:
    your_feature_key_v = file['your_feature_key_v'][:]
    your_target_key_v = file['your_target_key_v'][:]

# Process data
sampled_data = np.concatenate([your_feature_key_u, your_feature_key_v], axis=0)
target = np.concatenate([your_target_key_u, your_target_key_v], axis=0)

# Shuffle data
indices = np.arange(sampled_data.shape[0])
np.random.shuffle(indices)
sampled_data = sampled_data[indices]
target = target[indices]

# Data Splitting
X_train, X_test, y_train, y_test = train_test_split(sampled_data, target, test_size=0.2, random_state=42)

# Model Training and Prediction (using TensorFlow and Keras)
model = Sequential([
    layers.Dense(64, activation='relu', input_shape=(sampled_data.shape[1],)),
    layers.Dense(32, activation='relu'),
    layers.Dense(np.max(target) + 1, activation='softmax')  # Adjust the output layer based on your target classes
])

# Compile the model
model.compile(optimizer='adam', loss='sparse_categorical_crossentropy', metrics=['accuracy'])

# Train the model
history = model.fit(X_train, y_train, epochs=10, validation_data=(X_test, y_test))

# Predict using the test data
y_pred = model.predict(X_test)

# Evaluation
# Plot loss history
plt.plot(history.history['loss'], label='train_loss')
plt.plot(history.history['val_loss'], label='val_loss')
plt.legend()
plt.show()

# Confusion Matrix
conf_matrix = confusion_matrix(y_test, np.argmax(y_pred, axis=1))
print(conf_matrix)

# Other metrics if necessary
accuracy = accuracy_score(y_test, np.argmax(y_pred, axis=1))
print(f'Accuracy: {accuracy}')

# Random Forest Classifier Feature Selection
def feature_selection_using_rf(classifier, ntop, X_train, y_train):
    classifier.fit(X_train, y_train)
    feature_importances = classifier.feature_importances_
    selected_features = np.argsort(feature_importances)[-ntop:]
    return selected_features

def train_classifier_with_features(selected_features, X_train, y_train):
    clf = RandomForestClassifier(n_estimators=100, random_state=42)
    clf.fit(X_train[:, selected_features], y_train)
    return clf

# Record accuracies for different values of top features
ntop_values = [5, 10, 15, 20]  # Add more values as needed

accuracies_rf = []
for ntop in ntop_values:
    selected_features = feature_selection_using_rf(RandomForestClassifier(n_estimators=100, random_state=42), ntop, X_train, y_train)
    clf = train_classifier_with_features(selected_features, X_train, y_train)
    y_pred = clf.predict(X_test[:, selected_features])
    accuracy = accuracy_score(y_test, y_pred)
    accuracies_rf.append(accuracy)

# Plot accuracy vs the number of features
plt.plot(ntop_values, accuracies_rf, label='Random Forest Feature Selection')
plt.xlabel('Number of Features')
plt.ylabel('Accuracy')
plt.legend()
plt.show()

# SHAP Analysis Feature Selection
def feature_selection_using_shap(X_train, y_train):
    model = RandomForestClassifier(n_estimators=100, random_state=42)
    model.fit(X_train, y_train)
    explainer = shap.Explainer(model)
    shap_values = explainer.shap_values(X_train)
    feature_importances = np.abs(shap_values).mean(axis=0)
    selected_features_shap = np.argsort(feature_importances)[-ntop:]
    return selected_features_shap

selected_features_shap = feature_selection_using_shap(X_train, y_train)

clf_shap = train_classifier_with_features(selected_features_shap, X_train, y_train)
y_pred_shap = clf_shap.predict(X_test[:, selected_features_shap])
accuracy_shap = accuracy_score(y_test, y_pred_shap)
print(f'Accuracy with SHAP Analysis: {accuracy_shap}')

# Average Silhouette for K-Means Clustering
def plot_silhouette_analysis(X, labels, silhouette_values, n_clusters):
    fig, ax = plt.subplots()
    ax.set_xlim([-0.1, 1])
    ax.set_ylim([0, len(X) + (n_clusters + 1) * 10])

    y_lower = 10
    for i in range(n_clusters):
        ith_cluster_silhouette_values = silhouette_values[labels == i]
        ith_cluster_silhouette_values.sort()

        size_cluster_i = ith_cluster_silhouette_values.shape[0]
        y_upper = y_lower + size_cluster_i

        color = cm.nipy_spectral(float(i) / n_clusters)
        ax.fill_betweenx(np.arange(y_lower, y_upper),
                          0, ith_cluster_silhouette_values,
                          facecolor=color, edgecolor=color, alpha=0.7)

        ax.text(-0.05, y_lower + 0.5 * size_cluster_i, str(i))
        y_lower = y_upper + 10

# Perform k-means clustering with k values ranging from 2 to 12
k_values = range(2, 13)

for k in k_values:
    kmeans = KMeans(n_clusters=k, random_state=42)
    cluster_labels = kmeans.fit_predict(X_train)
    silhouette_avg = silhouette_score(X_train, cluster_labels)
    print(f"For n_clusters = {k}, the average silhouette score is: {silhouette_avg}")

    sample_silhouette_values = silhouette_samples(X_train, cluster_labels)
    plot_silhouette_analysis(X_train, cluster_labels, sample_silhouette_values, k)
    plt.show()
