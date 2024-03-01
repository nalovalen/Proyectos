{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
import Data.Functor.Reverse (Reverse)
{-# HLINT ignore "Use :" #-}
{-# HLINT ignore "Use null" #-}
{-# HLINT ignore "Use even" #-}
{-# HLINT ignore "Redundant ==" #-}
{-# HLINT ignore "Evaluate" #-}
{-# HLINT ignore "Redundant if" #-}

head1 :: [a] -> a
head1 (var:vars)= var

tail1 :: [a] -> [a]
tail1 (vars:s)= s

last1 :: [a]-> a
last1 [x] = x
last1 (x:xs)= last1 xs

init1 :: [a] -> [a]
init1 [x]=[]
init1 (x:xs)= [x] ++ init1 xs

maxtres :: Ord a => a-> a-> a -> a
maxtres a b c
    |a>=b && b>=c =a 
    |b>=a && b>=c =b
    |c>=a && c>=b =c

conc :: [a]-> [a] -> [a]
conc [] [] = []
conc [] ys = ys
conc xs [] = xs
conc (x:xs) ys = x : conc xs ys

tomar :: (Eq a) => Int -> [a]-> [a]
tomar a (x:xs)
    |a==0 || xs==[] = []
    |otherwise= x: tomar (a-1) xs

tirar :: Int -> [a]-> [a]
tirar 0 [] = []
tirar 0 xs = xs
tirar a xs = tirar (a-1) (tail1 xs)

abso :: Int-> Int
abso a
    |a<=0 = a*(-1)
    |otherwise = a

conca :: a -> [a] -> [a]
conca a [] = [a]
conca a (x:xs) = [a] ++ conca x xs

primo :: Int -> Bool
primo a
    |a<=1 = False
    |a>2 && (mod a 2 ==0) = False
    |a>=2 && all(\n -> mod a n /=0)[2..a-1] = True
    |otherwise = False

primenores :: Int -> [Int]
primenores a
    |a<=2 = [2]
    |a>=2 && primo a == True = [a] ++ primenores (a-1)
    |a>=2 && primo a == False = [] ++ primenores (a-1)

reversa :: [a]->[a]
reversa []=[]
reversa (x:xs)= reversa xs ++ [x]

iguales :: (Eq a)=> [a]->[a]->Bool
iguales x y= if x==y then True else False

cantraices :: Float -> Float -> Float -> Int
cantraices a b c
    |b^2-4*a*c == 0 =1
    |b^2-4*a*c < 0 =0
    |b^2-4*a*c > 0 =2

lpalindromo :: (Eq a)=> [a]->Bool
lpalindromo a = if a==reverse a then True else False

edad :: (Int, Int, Int)->(Int, Int, Int)->Int
edad (d, m, a) (c, e, l)
    |a==1 =0
    |a/=1 && m==e && c>=d =l-a
    |a/=1 && m==e && c<d =l-a-1
    |a/=1 && m>e =l-a-1
    |a/=1 && m<e =l-a

merge :: (Ord a)=>[a]->[a]->[a]
merge [] []= []
merge xs [] = xs
merge [] ys = ys
merge (x:xs) (y:ys)
    |head (x:xs)<= head (y:ys) = x : merge xs (y:ys)
    |head (y:ys) < head (x:xs) = y : merge (x:xs) ys

listnat :: [Int] -> [Int]
listnat [] = []
listnat (x:xs) =
    let menores = listnat [a | a <- xs, a <= x]
        mayores  = listnat [a | a <- xs, a > x]
    in  menores ++ [x] ++ mayores

pitagoricos :: Int -> [(Int, Int, Int)]
pitagoricos n = [(x, y, z) | x <- [1..n], y <- [x..n], z <- [y..n], x^2 + y^2 == z^2]

allP:: [(Int, Int, Int)]
allP = concat [pitagoricos n | n <-[0..]]

rev :: [Int] -> [Int]
rev [] = []
rev [x] = [x]
rev (x:xs) = rev xs ++ [x]

diag :: Int -> [(Int, Int)]
diag n = [(n-i, i) | i<- [0..n]]

allPairs :: [(Int, Int)]
allPairs = concat [diag n | n <-[0..]]