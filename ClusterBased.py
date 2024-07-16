#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Random Forest Classifier Feature Selection
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score

# Define the classifier
rf_classifier = RandomForestClassifier(n_estimators=100, random_state=42)

# Record accuracies for different values of top features
ntop_values = [5, 10, 15, 20, ...]  # Add more values as needed

accuracies_rf = []
for ntop in ntop_values:
    # Feature selection
    selected_features = feature_selection_using_rf(rf_classifier, ntop, X_train, y_train)

    # Train a classifier with selected features
    clf = train_classifier_with_features(selected_features, X_train, y_train)

    # Make predictions on test data
    y_pred = clf.predict(X_test)

    # Calculate accuracy
    accuracy = accuracy_score(y_test, y_pred)
    accuracies_rf.append(accuracy)

# Plot accuracy vs the number of features
plt.plot(ntop_values, accuracies_rf, label='Random Forest Feature Selection')
plt.xlabel('Number of Features')
plt.ylabel('Accuracy')
plt.legend()
plt.show()

# SHAP Analysis Feature Selection
import shap

# Feature selection using SHAP values
selected_features_shap = feature_selection_using_shap(X_train, y_train)

# Train a classifier with selected features
clf_shap = train_classifier_with_features(selected_features_shap, X_train, y_train)

# Make predictions on test data
y_pred_shap = clf_shap.predict(X_test)

# Calculate accuracy
accuracy_shap = accuracy_score(y_test, y_pred_shap)

# Print accuracy with SHAP analysis
print(f'Accuracy with SHAP Analysis: {accuracy_shap}')


