def quick_sort(arr):
  # 递归终止条件：当列表长度小于等于1时，直接返回
  if len(arr) <= 1:
    return arr

  # 选择基准值（这里选择第一个元素）
  pivot = arr[0]

  # 分别存放小于、等于和大于 pivot 的元素
  left = [x for x in arr[1:] if x < pivot]
  middle = [x for x in arr if x == pivot]
  right = [x for x in arr[1:] if x > pivot]

  # 递归排序并拼接结果
  return quick_sort(left) + middle + quick_sort(right)

# 示例
arr = [3, 6, 8, 10, 1, 2, 1]
sorted_arr = quick_sort(arr)
print("排序结果：", sorted_arr)
