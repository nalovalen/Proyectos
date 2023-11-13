{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Use sum" #-}
{-# HLINT ignore "Fuse foldr/map" #-}
{-# HLINT ignore "Eta reduce" #-}
{-# HLINT ignore "Use and" #-}
{-# HLINT ignore "Use product" #-}
cuadrados :: Int->Int
cuadrados x = x*x

sumcua :: [Int]->Int
sumcua (x:xs)= foldr (+) 0 (map cuadrados (x:xs))

listsucc :: [Int]->[Int]
listsucc []=[]
listsucc xs= map succ xs

sumlist :: [Int]->Int
sumlist []= 0
sumlist xs= foldr (+) 0 xs

factfold :: Int->Int
factfold n= foldr (*) 1 [1..n]

andred :: [Bool]->Bool
andred xs= foldr (&&) True xs