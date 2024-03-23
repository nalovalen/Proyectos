--Implementacion de permutaciones de una lista
permutaciones :: (Eq a)=> [a] -> [[a]]
permutaciones [] = [[]]
permutaciones xs = [x:p| x<-xs, p<- permutaciones(remove x xs)]


--Implementacion de todas las sublistas de una lista
sublistas :: [a] -> [[a]]
sublistas [] = [[]]
sublistas (x:xs) = sublistas xs ++ map (x:) (sublistas xs)

remove :: (Eq a)=> a -> [a]->[a]
remove a [] = []
remove a (x:xs)
    |a==x = xs
    |otherwise = x : remove a xs

--Implementacion de los anagramas
anagrama :: [Char] -> [Char] -> Bool
anagrama [] [] = True
anagrama [] _ = False
anagrama _ [] = False
anagrama xs ys = igual ms xs
    where
        ms = permutaciones ys

--Implementacion de si la suma de los elementos de un conjunto es igual a un numero
sumList :: [Int]->Int->Bool
sumList [] n = n==0
sumList xs n = subSum (sublistas xs) n
--Metodo auciliar de sumList
subSum :: [[Int]]->Int->Bool
subSum [[]] n = n==0
subSum (x:xs) n = sum x == n|| subSum xs n

--Metodo auxiliar de anagrama
igual :: (Eq a)=>[[a]]->[a]->Bool
igual [[]] []= True
igual [[]] ys = False
igual xs [] = False
igual (x:xs) ys =  mismas x ys || igual xs ys

--Metodo auxiliar de igual
mismas :: (Eq a)=>[a]->[a]->Bool
mismas [] []= True
mismas _ []= False
mismas [] _ = False
mismas (x:xs) (y:ys) = x==y && mismas xs ys


