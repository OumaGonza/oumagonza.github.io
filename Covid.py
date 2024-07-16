import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

# Load data from the New York Times dataset (replace 'nyt_data.csv' with your file)
us_states = pd.read_csv('/home/katana/Kanairo/Python&ML/us-states.csv')

# Load data from the FiveThirtyEight dataset (replace '538_data.csv' with your file)
fivethirtyeight_partisan_lean_STATES = pd.read_csv('/home/katana/Kanairo/Python&ML/fivethirtyeight_partisan_lean_STATES.csv')

# Combine daily data for cases_avg_per_100k and deaths_avg_per_100k by averaging within each state
combined_data = us_states.groupby(['state', 'date']).agg({
    'cases_avg_per_100k': 'mean',
    'deaths_avg_per_100k': 'mean'
}).reset_index()

# Extract the year and month from the 'date' column
combined_data['year'] = pd.to_datetime(combined_data['date']).dt.year
combined_data['month'] = pd.to_datetime(combined_data['date']).dt.month

# Merge with FiveThirtyEight data based on the 'state' column
combined_data = pd.merge(combined_data, fivethirtyeight_partisan_lean_STATES, on='state')

# Rename the 2021 variable column heading as 'partisan_leaning'
combined_data.rename(columns={'2021': 'partisan_leaning'}, inplace=True)

# Rename cases_avg_per_100k as cases_avg_per_100k_mean
combined_data.rename(columns={'cases_avg_per_100k': 'cases_avg_per_100k_mean'}, inplace=True)

# Rename deaths_avg_per_100k as deaths_avg_per_100k_mean
combined_data.rename(columns={'deaths_avg_per_100k': 'deaths_avg_per_100k_mean'}, inplace=True)

# Extract the 'date' column from the 'us_states' DataFrame
date_column = us_states[['date']]

# Drop the 'date' column from the 'us_states' DataFrame
us_states.drop(columns=['date'], inplace=True)

# Concatenate the 'date' DataFrame with the 'combined_data' DataFrame as the first column
combined_data = pd.concat([date_column, combined_data], axis=1)

# Remove the second 'date' column
combined_data = combined_data.loc[:, ~combined_data.columns.duplicated()]

# Rearrange columns in the desired order
combined_data = combined_data[['date', 'state', 'cases_avg_per_100k_mean', 'deaths_avg_per_100k_mean', 'partisan_leaning']]
print(combined_data)

# Create a new column 'red_blue' based on 'partisan_leaning' values
combined_data['red_blue'] = 'red'  # Default to 'red'
combined_data.loc[combined_data['partisan_leaning'] > 0, 'red_blue'] = 'blue'  # Change to 'blue' if 'partisan_leaning' is positive

# Group by 'red_blue' and calculate the means
means_by_red_blue = combined_data.groupby('red_blue').agg({
    'cases_avg_per_100k_mean': 'mean',
    'deaths_avg_per_100k_mean': 'mean'
})

# Rename the columns for clarity
means_by_red_blue.columns = ['Mean Cases (red/blue)', 'Mean Deaths (red/blue)']

# Print the means for red and blue states
print(means_by_red_blue)

# Group by 'date' and 'red_blue', and calculate the means
means_by_month_red_blue = combined_data.groupby(['date', 'red_blue']).agg({
    'cases_avg_per_100k_mean': 'mean',
    'deaths_avg_per_100k_mean': 'mean'
}).unstack()  # Unstack to create separate columns for red and blue

# Reset the index for better display
means_by_month_red_blue.reset_index(inplace=True)

# Create line chart for cases_avg_per_100k
plt.figure(figsize=(12, 6))
plt.title("Time Trend for Cases per 100k - Red vs. Blue States")
plt.xlabel("date")
plt.ylabel("cases_avg_per_100k_mean")

# Plot red states data
plt.plot(means_by_month_red_blue['date'], means_by_month_red_blue['cases_avg_per_100k_mean']['red'], label='Red States', color='red')

# Plot blue states data
plt.plot(means_by_month_red_blue['date'], means_by_month_red_blue['cases_avg_per_100k_mean']['blue'], label='Blue States', color='blue')

# Rotate x-axis labels for better readability
plt.xticks(rotation=45)

# Display legend
plt.legend()

# Show the plot
plt.tight_layout()
plt.show()

# Create line chart for deaths_avg_per_100k
plt.figure(figsize=(12, 6))
plt.title("Time Trend for Deaths per 100k - Red vs. Blue States")
plt.xlabel("date")
plt.ylabel("deaths_avg_per_100k_mean")

# Plot red states data
plt.plot(means_by_month_red_blue['date'], means_by_month_red_blue['deaths_avg_per_100k_mean']['red'], label='Red States', color='red')

# Plot blue states data
plt.plot(means_by_month_red_blue['date'], means_by_month_red_blue['deaths_avg_per_100k_mean']['blue'], label='Blue States', color='blue')

# Rotate x-axis labels for better readability
plt.xticks(rotation=45)

# Display legend
plt.legend()

# Show the plot
plt.tight_layout()
plt.show()
