import pandas as pd

# Load the data
housing_data = pd.read_csv('housing_data.zip')  # Make sure to provide the correct file path
msa_codes = pd.read_csv('/home/katana/Kanairo/Python&ML/msacodes(1).csv')  # Make sure to provide the correct file path

# Merge the housing data with MSA codes
merged_data = pd.merge(housing_data, msa_codes, how='left', left_on='MET2013', right_on='MSACode')

# Convert VALUEH to numeric, treating 999999 as NaN
merged_data['VALUEH'] = pd.to_numeric(merged_data['VALUEH'], errors='coerce')

# Filter data for homeowners in 2019
homeowners_2019 = merged_data[(merged_data['ERSHP'] == 1) & (merged_data['YEAR'] == 2019)]

# Group by MSA and calculate median household income and house value
msa_stats_2019 = homeowners_2019.groupby('MSAName').agg({
    'HHINCOME': 'median',
    'VALUEH': 'median',
    'ERSHP': 'count'
}).reset_index()

# Calculate the ratio of median house value to median household income
msa_stats_2019['PriceIncomeRatio'] = msa_stats_2019['VALUEH'] / msa_stats_2019['HHINCOME']

# Calculate the growth rate in the number of households between 2019 and 2022
homeowners_2022 = merged_data[(merged_data['ERSHP'] == 1) & (merged_data['YEAR'] == 2022)]
msa_stats_2022 = homeowners_2022.groupby('MSAName')['ERSHP'].count().reset_index()
msa_stats_2022.columns = ['MSAName', 'HouseholdCount2022']
msa_stats = pd.merge(msa_stats_2019, msa_stats_2022, how='left', on='MSAName')
msa_stats['GrowthRate'] = (msa_stats['HouseholdCount2022'] - msa_stats['ERSHP']) / msa_stats['ERSHP'] * 100

# Task 2: List the top 10 most affordable MSAs in 2022
top_10_affordable = msa_stats.sort_values('PriceIncomeRatio').head(10)

# Task 3: Scatterplot for the top 50 most populated MSAs
top_50_populated = msa_stats.sort_values('ERSHP', ascending=False).head(50)

import matplotlib.pyplot as plt

plt.figure(figsize=(10, 6))
plt.scatter(top_50_populated['PriceIncomeRatio'], top_50_populated['GrowthRate'])
plt.title('Scatterplot of Price/Income Ratio vs Population Growth (2019-2022)')
plt.xlabel('Price/Income Ratio')
plt.ylabel('Population Growth Rate (%)')
plt.show()
