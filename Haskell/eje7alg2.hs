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
anagrama [] ys = False
anagrama xs [] = False
anagrama xs ys = igual ms ys
    where
        ms = permutaciones xs

--Implementacion de si la suma de los elementos de un conjunto es igual a un numero
sumList :: [Int]->Int->Bool
sumList [] 0 = True
sumList [] n = False
sumList xs n = subSum (sublistas xs) n


--Metodo auciliar de sumList
subSum :: [[Int]]->Int->Bool
subSum [] n = False
subSum (x:xs) n = (sum x == n) || subSum xs n

--Metodo auxiliar de anagrama
igual :: (Eq a)=>[[a]]->[a]->Bool
igual [] []= True
igual [] ys = False
igual (x:xs) ys =  mismas x ys || igual xs ys

--Metodo auxiliar de igual
mismas :: (Eq a)=>[a]->[a]->Bool
mismas [] []= True
mismas xs []= False
mismas [] ys = False
mismas (x:xs) (y:ys) = x==y && mismas xs ys

--Implementacion de si una cadena es subcadena de otra
subcadena :: [Char]->[Char]->Bool
subcadena [] [] = True
subcadena [] ys = True
subcadena xs [] = False
subcadena xs ys = igual ms xs
    where
        ms = sublistas ys


subsec :: [Char]->[Char]->Bool
subsec [] [] = True
subsec xs [] = False
subsec [] ys = True
subsec (x:xs) (y:ys)
    |x==y = subsec xs ys
    |x/=y = subsec (x:xs) ys