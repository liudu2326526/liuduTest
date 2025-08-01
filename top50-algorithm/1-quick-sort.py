def quick_sort(arr, left, right):
  """
  原地快排
  :param arr: 待排序的列表
  :param left: 左索引
  :param right: 右索引
  :return:
  """
  # 终止条件
  if right > left:
    pivot_index = partition(arr, left, right)
    quick_sort(arr, left, pivot_index - 1)
    quick_sort(arr, pivot_index + 1, right)
  return arr


def partition(arr, low, high):
  """
  分区函数，将数组分为两部分，使得 pivot 左边都小于 pivot，右边都大于 pivot。
  :param arr: 待排序的列表
  :param low: 分区起始索引
  :param high: 分区结束索引（pivot 选择此位置的值）
  :return: pivot 排序后的正确索引位置
  """

  pivot = arr[high]
  i = low - 1

  for j in range(low, high):
    if arr[j] < pivot:
      i += 1
      arr[i], arr[j] = arr[j], arr[i]

  arr[i + 1], arr[high] = arr[high], arr[i + 1]

  return i + 1


# 示例
arr = [6, 3, 1, 10, 1, 2, 8]
sorted_arr = quick_sort(arr, 0, len(arr) - 1)
# sorted_arr = quick_sort(arr)
print("排序结果：", arr)
print(sorted_arr)
