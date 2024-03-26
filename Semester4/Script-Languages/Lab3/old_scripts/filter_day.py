from datetime import datetime

def filter_records_by_day_name(records, day_name):
    filtered_records = []
    date_format = "%d/%b/%Y:%H:%M:%S"
    target_day_index = parse_day_name(day_name)  # Convert day name to its index
    for record in records:
        if datetime.strptime(record['date'], date_format).weekday() == target_day_index:
            filtered_records.append(record)
    return filtered_records

def print_filtered_records(records):
    day_name = "Friday"
    filtered_records = filter_records_by_day_name(records, day_name)
    for record in filtered_records:
        print(record['raw_string'])

def parse_day_name(day_name):
    # Map day name to its index
    day_names = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
    return day_names.index(day_name)

if __name__ == "__main__":
    from sys import stdin
    import record_parser
    
    records = record_parser.parse(stdin.readlines())
    print_filtered_records(records)