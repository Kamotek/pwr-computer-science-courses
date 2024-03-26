def count_images(records):
    file_extensions = {
    '*.gif': 0,
    '*.jpg': 0,
    '*.jpeg': 0,
    '*.xbm': 0,
    }
    for record in records:
        if record['path'].endswith('.gif'):
            file_extensions['*.gif'] += 1
        elif record['path'].endswith('.jpg'):
            file_extensions['*.jpg'] += 1
        elif record['path'].endswith('.jpeg'):
            file_extensions['*.jpeg'] += 1
        elif record['path'].endswith('.xbm'):
            file_extensions['*.xbm'] += 1
    return file_extensions


def show_images_number_and_ratio(records):
    file_extensions = count_images(records)
    print(f"Number of .gif files: {file_extensions['*.gif']}, Ratio: {file_extensions['*.gif']/len(records):.5f}")
    print(f"Number of .jpg files: {file_extensions['*.jpg']}, Ratio: {file_extensions['*.jpg']/len(records):.5f}")
    print(f"Number of .jpeg files: {file_extensions['*.jpeg']}, Ratio: {file_extensions['*.jpeg']/len(records):.5f}")
    print(f"Number of .xbm files: {file_extensions['*.xbm']}, Ratio: {file_extensions['*.xbm']/len(records):.5f}")

