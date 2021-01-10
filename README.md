# HeuristicSearch
Project for AI course purposes - heuristic search. Comparision of Dijkstry algorithm from JUNG library and self-implemented A* algorithm

Projekt rozwiązuje problem znalezienia ścieżki o najniższym koszcie między dwoma
wierzchołkami grafu. Został on napisany w języku Java z wykorzystaniem biblioteki JUNG
(Java Universal Network/Graph Framework). Rozwiązanie problemu zostało wyznaczone
dwoma sposobami. Pierwszy z nich to algorytm Dijkstry, który wyznacza ścieżkę o najniższym
koszcie korzystając wyłącznie z wag krawędzi. Drugi z nich to algorytm A*, który korzysta z
funkcji oceny:
f(n) = g(n) + h(n)
Gdzie:
g(n) – koszt dotarcia do wierzchołka n
h(n) – szacowany koszt dotarcia z wierzchołka n do wierzchołka docelowego
Znajdowanie ścieżki o najniższym koszcie jest problemem obecnym zarówno w życiu
codziennym, jak i w świecie informatyki. Algorytm A* jest często wykorzystywany w grach
oraz mapach.

Przykładowy problem (Plik dane.txt)
Projekt rozwiązuje problem znalezienia ścieżki z punktu startowego do punktu docelowego,
która zajmie najmniej czasu. W tym przypadku rozważamy trasę z centrum Białegostoku do
Politechniki Białostockiej. Koszt każdej krawędzi reprezentującej poszczególne odcinki trasy
jest wyrażony w minutach. Funkcją heurystyczną każdego wierzchołka jest przybliżony czas
dotarcia do punktu docelowego również wyrażony w minutach.
