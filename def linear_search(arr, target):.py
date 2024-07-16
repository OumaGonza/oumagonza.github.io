def linear_search(arr, target):
    comparisons = 0
    for i, value in enumerate(arr):
        comparisons += 1
        if value == target:
            return i, comparisons  # Return the index and the number of comparisons
    return -1, comparisons  # If the target is not found, return -1 and the number of comparisons

# Example usage
my_list = [2, 5, 7, 1, 8, 3]
target_value = 8

index, num_comparisons = linear_search(my_list, target_value)

if index != -1:
    print(f'Target {target_value} found at index {index} with {num_comparisons} comparisons.')
else:
    print(f'Target {target_value} not found with {num_comparisons} comparisons.')
