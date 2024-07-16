#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import numpy as np
import matplotlib.pyplot as plt
from scipy.stats import norm

def brownian_motion(T, N):
    dt = T / N
    t = np.linspace(0.0, T, N+1)
    W = np.cumsum(np.sqrt(dt) * np.random.randn(N))
    return t, W

def first_hitting_time(m, T, N, num_simulations):
    hitting_times = np.zeros(num_simulations)

    for i in range(num_simulations):
        t, W = brownian_motion(T, N)
        crossing_indices = np.where(np.abs(W) >= m)[0]
        
        if len(crossing_indices) > 0:
            hitting_times[i] = t[crossing_indices[0]]

    return hitting_times[hitting_times > 0]

def theoretical_probability(m, t):
    return 2 * norm.cdf(-abs(m) / np.sqrt(t))

def empirical_probability(hitting_times, t):
    return np.sum(hitting_times <= t) / len(hitting_times)

def plot_probabilities(m_values, T, N, num_simulations):
    for m in m_values:
        hitting_times = first_hitting_time(m, T, N, num_simulations)
        t_values = np.linspace(0, T, 100)
        theoretical_probabilities = theoretical_probability(m, t_values)
        empirical_probabilities = [empirical_probability(hitting_times, t) for t in t_values]

        plt.figure()
        plt.plot(t_values, theoretical_probabilities, label='Theoretical Probability')
        plt.plot(t_values, empirical_probabilities, label='Empirical Probability')
        plt.title(f'First-Hitting Time Probability for m={m}')
        plt.xlabel('Time (t)')
        plt.ylabel(f'Probability P(τ{m} ≤ t)')
        plt.legend()
        plt.show()

# Parameters
m_values = [0.5, 1.0]
T = 1.0
N = 1000
num_simulations = 10000

# Plot probabilities
plot_probabilities(m_values, T, N, num_simulations)


