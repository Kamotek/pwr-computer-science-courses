def find_max_size(records):
    max_size = 0
    path = ''
    for record in records:
        if(record['bytes'] > max_size):
            max_size = record['bytes']
            path = record['path']
    return max_size, path


def show_max_size_and_path(records):
    size, path = find_max_size(records)
    print(f"Max size: {size} bytes, Path: {path}")
