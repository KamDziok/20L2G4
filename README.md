# Programowanie Zespołowe - Ankiety (20L2G4)

### Opis projektu

Aplikacja Panelu Ankietowego ma na celu zdobycie cennych informacji od użytkowników wypełniających ankiety w zamian za nagrody.

Aby korzystać z aplikacji zwykły użytkownik, który wypełnia ankiety musi się zarejestrować, a następnie zalogować. Po poprawnym zalogowaniu użytkownik ma dostęp do aktywnych ankiet, których nie wypełnił wraz z liczbą punków, które może uzyskać za daną ankietę. Zalogowany użytkownik ma dostęp do historii wypełniania ankiet w formie listy z uzyskaną liczby punków za daną ankietę, historia uwzględnia również wymianę punktów na nagrody. Panel użytkownika daje możliwość edycji profilu. W panelu nagród użytkownik może wymienić zgromadzone punkty na dostępne nagrody, informacja wizualna przedstawia czy użytkownik ma wystarczającą liczbę punktów na wymianę.

Twórca ankiet ma dostęp do stworzonych przez siebie ankiet, może generować raporty, gdy ankieta dobiegła końca, przedłużać lub skracać ważność ankiety, a jeśli istnieje taka potrzeba również zakończyć ankietę. Twórca ankiet może również tworzyć nowe ankiety poprzez generator ankiet, w którym ma możliwość zredagowania pytanie w formie tekstu lub zdjęcia oraz odpowiedzi na dane pytanie. Odpowiedzi mogą mieć wiele form od pól typu checkbox z kilkoma możliwymi odpowiedziami do zaznaczenia, przez pola z jedną możliwą odpowiedzią, po pola tekstowe. Możliwe będą również pola, gdzie trzeba będzie rozdzielić punkty/procenty na kilka odpowiedzi i ich pełna pula musi zostać rozdzielona. Do pól typu checbkox twórca ankiety będzie mógł określić maksymalną i minimalną liczbę odpowiedzi. Każda ankieta może mieć dowolną liczbę punktów do uzyskania wprowadzoną przez twórcę, pod warunkiem spełnienia założenia, że na jedno pytanie z ankiety nie przypada więcej niż 10 punków.

Osoba odpowiedzialna za nagrody ma możliwość podglądu i edycji już istniejących nagród oraz może dodawać nowe nagrody. Nagrody mogą być w formie generowanego kuponu lub przedmiotu wysyłanego na adres domowy użytkownika.

Administrator ma dostęp do kont użytkowników, może nadawać im uprawnienia twórcy ankiet lub osoby odpowiedzialnej za nagrody, a także może blokować dostęp do konta.


### Diagram przypadków użycia

![diagram przypadków użycia](./diagramy/DiagramPrzypadkowUzycia.jpg)

### Diagram klas

![diagram klas](./diagramy/Ankieta.png)

### Diagram encji

![diagram encji](./diagramy/DiagramEncji.png)

### Diagramy aktywności:

##### - użytkownika

![diagram aktywności użytkownika](./diagramy/diagramaktywności.jpg)

##### - administratora

![diagram aktywności administratora](./diagramy/diagramaktywnościAdministrator.jpg)

##### - twórcy ankiet

![diagram aktywności twórcy ankiet](./diagramy/diagramaktywnościTwórca.jpg)

##### - twórcy nagród

![diagram aktywności twórcy nagród](./diagramy/diagramaktywnościTwórcaNagród.jpg)

### Diagram stanów ankiety

![diagram stanów](./diagramy/diagramStanow.jpg)
