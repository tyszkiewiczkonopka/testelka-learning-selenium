# Selenium WebDriver 4 w Javie

**Prowadząca**: Ela Sądel (testelka.pl)

## Instalacja narzędzi
1. IntelliJ i pluginy
2. JDK
3. Testowana aplikacja
4. Docker
5. Aplikacja na Dockerze
6. Git
7. Dodatkowe aplikacje

## Absolutne podstawy
1. JUnit
2. Selenium i pierwszy test
3. Przeglądarki
4. Asercja
5. Narzędzia deweloperskie
6. Szukanie elementów
7. Interakcje z elementami
8. Wprowadzanie tekstu

## Solidne podstawy
1. Testowanie asercji
2. Nazwy testów
3. Timeout na wyszukiwanie elementów
4. Timeout na załadowanie strony 
5. Jak łapać „znikające” elementy 
6. Czekanie na warunki: ExpectedConditions
7. Czekanie na własne warunki

## Informacje o elementach
1. isEnabled()
2. isDisplayed()
3. isSelected() i szukanie wielu elementów
4. getDomAttribute()
5. getDomProperty
6. Pozostałe informacje o elementach

## To ci się przyda
1. Szukanie elementów w elementach 
2. Robimy porządki: BaseTests 
3. Robimy porządki: baseUrl 
4. Inne przeglądarki niż Chrome 
5. Headless 
6. Selektory CSS czy XPath? 
7. Sprzątanie po testach i dane testowe

## Selektory CSS
1. Co to jest CSS i do czego służą selektory CSS
2. Tagi i atrybuty w selektorach CSS  
3. Selektory specjalne – id i klasa elementu 
4. Zadanie: atrybuty w selektorach CSS
5. Fragmenty wartości atrybutów
6. Dzieci, potomkowie, bracia
7. Zadanie: dzieci, potomkowie, bracia
8. Selektory pseudoklas: pozycja 
9. Selektory pseudoklas: typy i pusty element 
10. Selektor negacji

## XPath
1. Co to jest XPath i do czego służy
2. Atrybuty i tagi
3. Fragment atrybutu i szukanie po tekście
4. Zadanie: atrybuty, tagi i tekst
5. Potomkowie i dzieci w XPath
6. Rodzeństwo, przodkowie i rodzice w XPath
7. Pozycja w XPath
8. Zadanie: relacje
9. Negacja

## Co może pójść nie tak
1. Ramki: wprowadzenie
2. Ramki w praktyce
3. Czekanie na ramki
4. Shadow DOM
5. Shadow DOM w Firefox
6. Co w konsoli piszczy
7. NoSuchElementException
8. StaleElementReferenceException
9. InvalidSelectorException
10. ElementClickInterceptedException
11. ElementNotInteractableException

## Page Object Model i organizacja testów

1. Jak będzie wyglądał ten moduł
2. Bot Pattern
3. Bot Pattern jeszcze raz
4. Przygotowanie
5. Pierwsze klasy PO: CartPage
6. Kolejna klasa PO: ProductPage
7. BasePage
8. BasePage i BaseURL
9. Wspólne elementy stron i uporządkowanie
10. Wspólne elementy: dziedziczenie
11. Wspólne elementy: kompozycja
12. tests.BaseTest
13. Zmiana przeglądarki
14. Konfiguracja z pliku
15. Powrót do drivera
16. Klasa Browser
17. Wait w Browser
18. PageFactory

## Selenium Grid
1. Grid: zanim zaczniemy 
2. VPS 
3. Instalacja aplikacji na VPSie 
4. Grid: uruchamianie 
5. Konfiguracja 
6. RemoteWebDriver 
7. Uruchamianie testów w Gridzie 
8. Grid: Docker 
9. Dodatkowy node 
10. Testy na wybranej wersji przeglądarki

## Cuda na kiju, czyli dodatki
1. Testy równoległe w JUnit
2. Waity na sterydach: FluentWait 
3. Rozmiar i pozycja okna przeglądarki 
4. Dodatkowa nawigacja i źródło strony 
5. Wgrywanie pliku z dysku 
6. Ciasteczka 
7. Listy rozwijane 
8. Drukowanie strony do PDFa 
9. Zrzuty ekranu i elementów 
10. Zrzut ekranu na fail testu 
11. Wykonywanie JavaScriptów 
12. Logi w konsoli przeglądarki 
13. Nasłuchiwanie wyjątków JavaScript 
14. Wykrywanie zmian w DOM 
15. Przechwytywanie ruchu sieciowego 
16. Basic Auth 
17. WebStorage 
18. Alerty 
19. Kilka okien/kart przeglądarki 
20. Selektory CSS: has()
21. Względne lokatory 
22. Actions API 
23. Actions API: ctrl + click 
24. Actions API: przeciągnij i upuść