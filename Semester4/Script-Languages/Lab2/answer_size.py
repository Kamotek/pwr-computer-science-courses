def calc_size(records):
    size = 0
    for record in records:
        size += record['bytes']
    size /= 1024 * 1024 * 1024 # convert to GB
    return size


def show_size(records):
    size = calc_size(records)
    print(f"Total size: {size:.2f} GB")
