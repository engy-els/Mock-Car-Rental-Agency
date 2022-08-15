cars - array containing the list of cars
low - index of the first item in the list (or sub-list)
high - index of the last item in the list (or sub-list)

mergesort(cars [], low, high)
    if low < high then
        mid = (low + high) / 2
        mergesort(cars[], low, mid)
        mergesort(cars[], mid + 1, high)
        merge(cars[], low, mid, high)
    end if
end mergesort

merge(cars[], start, mid, end)
    create temp array to be the same size as cars

    pos1 = start
    pos2 = mid + 1
    index = start

    while pos1 <= mid and pos2 <= end do
        if cars[pos2] < cars[pos1] then
            temp[index] = cars[pos2]
            increment pos2 and index
        else
            temp[index] = cars[pos1]
            increment pos1 and index
        end if
    end while

    while pos1 <= mid do
        temp[index] = cars[pos1]
        increment pos1 and index
    end while

    while pos2 <= end do
        temp[index] = cars[pos2]
        increment pos2 and index
    end while

    copy all elements of temp to cars
end merge