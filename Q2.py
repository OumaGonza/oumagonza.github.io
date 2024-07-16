import matplotlib.pyplot as plt
import numpy as np
from scipy.optimize import fsolve

# Task 1: Define a vector of 100 evenly spaced fund returns from -10% to 40%
fund_returns = np.linspace(-10, 40, 100)

# Task 2: Calculate investor's and firm's total return for each fund return
def calculate_returns(fund_return, hurdle_rates=(7, 9, 15), carry_rates=(0, 0.2, 0.4)):
    preferred_return = min(fund_return, hurdle_rates[0]) * 1.0
    catch_up_return = max(0, min(fund_return - hurdle_rates[0], hurdle_rates[1] - hurdle_rates[0])) * 1.0
    carry_1_return = max(0, min(fund_return - hurdle_rates[1], hurdle_rates[2] - hurdle_rates[1])) * carry_rates[1]
    carry_2_return = max(0, fund_return - hurdle_rates[2]) * carry_rates[2]
    
    total_return = preferred_return + catch_up_return + carry_1_return + carry_2_return
    firm_return = 1.0 - total_return
    
    return total_return, firm_return

# Task 3: Plot the investor's and the firm's total return as a function of the fund return
investor_returns, firm_returns = np.vectorize(calculate_returns)(fund_returns)

plt.figure(figsize=(10, 6))
plt.plot(fund_returns, investor_returns, label="Investor's Return")
plt.plot(fund_returns, firm_returns, label="Firm's Return")
plt.xlabel('Fund Return (%)')
plt.ylabel('Total Return')
plt.title('Investor\'s and Firm\'s Total Return vs Fund Return')
plt.legend()
plt.grid(True)
plt.show()

# Task 4: Find the value of the final hurdle rate using fsolve
def equation(final_hurdle_rate, target_investor_return, target_fund_return):
    updated_hurdle_rates = (7, 9, final_hurdle_rate)
    updated_investor_return, _ = calculate_returns(target_fund_return, hurdle_rates=updated_hurdle_rates)
    return updated_investor_return - target_investor_return

# Example: If the fund returns 25% and the investor wants 17% return
target_investor_return = 0.17
target_fund_return = 25.0
initial_guess = 15.0  # Starting with the current hurdle rate

final_hurdle_rate = fsolve(equation, initial_guess, args=(target_investor_return, target_fund_return))[0]
print(f"The final hurdle rate to achieve {target_investor_return*100}% investor return at {target_fund_return}% fund return is: {final_hurdle_rate}%")
