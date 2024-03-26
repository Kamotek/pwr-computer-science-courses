from sys import stdin

import answer_codes
import record_parser
import answer_size
import answer_max_size
import answer_images_ratio

import filter_pl
import filter_day
import filter_responses
import filter_time

def main():
    records = record_parser.parse(stdin.readlines())
    answer_codes.show_counts(records)
    answer_size.show_size(records)
    answer_max_size.show_max_size_and_path(records)
    answer_images_ratio.show_images_number_and_ratio(records)

    #filter_pl.print_filtered_records(records)
    #filter_day.print_filtered_records(records)
    filter_time.show_within_hours(records)
    


if __name__ == "__main__":
    main()
