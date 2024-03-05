
import wikipedia

def get_wikipedia_summary_and_url(article_name):
        try:
            # Tworzymy zmienne pobierające dane z wikipedii i je zwracamy
            article_summary = wikipedia.summary(article_name)
            article_url = wikipedia.page(article_name).url
            return article_summary, article_url
        except wikipedia.exceptions.DisambiguationError as e:
            # jezeli nazwa artykułu jest niejednoznaczna i dostajemy wyjatek to go przechwuujemy i wyswietlamy opcje
            print("The article name is ambiguous. Please select one of the following options:")
            # wyświetlamy opcje iterujac po kolei
            for i, option in enumerate(e.options, 1): 
                print(f"{i}. {option}")
            choice = input("Enter the number corresponding to your choice: ")
            # jezeli wybór jest poprawny to przypisujemy nazwe artykulu
            if choice.isdigit() and 1 <= int(choice) <= len(e.options):
                article_name = e.options[int(choice) - 1]
            else:
                # jezeli wybór jest niepoprawny to zwracamy None
                print("Invalid choice.")
                return None, None
        except wikipedia.exceptions.PageError:
            # Jezeli nie ma takiego artykulu wyswietlamy napis i zwracamy None, None
            print("The article could not be found.")
            return None, None

# uzycie funkcji
article_name = input("Enter the name of the Wikipedia article: ")
article_summary, article_url = get_wikipedia_summary_and_url(article_name)
if article_summary is not None:
    print("Summary:")
    print(article_summary)
    print("URL to the Wikipedia page:")
    print(article_url)
