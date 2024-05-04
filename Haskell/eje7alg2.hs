--Implementacion de permutaciones de una lista
{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Use foldr" #-}
permutaciones :: (Eq a)=> [a] -> [[a]]
permutaciones [] = [[]]
permutaciones xs = [x:p| x<-xs, p<- permutaciones(remove x xs)]

--Implementacion de los subconjuntos de un conjunto
subconj :: [a]->[[a]]
subconj [] = [[]]
subconj (x:xs) = subconj xs ++ map (x:) (subconj xs) 

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


dieta :: [Int]->Bool
dieta (x:xs) = or[sum y == 0 && length y /= 0|y<- ps ]
    where
        ps = subconj (x:xs)

data Nat = Zero | Succ Nat

instance Eq Nat where
    (==) :: Nat -> Nat -> Bool
    Zero == Zero = True
    Succ m == Zero = False
    Zero == Succ n = False
    Succ m == Succ n = n==m

instance Show Nat where
    show Zero = "Zero"
    show (Succ n) = "Succ (" ++ show n ++ ")"


data Tree a = Nil | Node a (Tree a) (Tree a)

instance Eq a => Eq (Tree a) where
    Nil == Nil = True
    (Node a hi hd) == (Node b hi1 hd1) = a == b && hi1 == hi && hd == hd1
    _ == _ = False

instance Show a => Show (Tree a) where
    show Nil = "Nill"
    show (Node a hi hd) = "(" ++ show hi ++ "," ++ show a ++ "," ++ show hd ++ ")"