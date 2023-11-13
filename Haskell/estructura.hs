{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Use null" #-}
{-# OPTIONS_GHC -Wno-missing-methods #-}
{-|
Module      : StackList
Description : Provides an implementation using lists for the Stack ADT
Copyright   : Estructuras de Datos y Alg. UNRC
License     : GPL-3
We use haddock style of comments
-}

module ColaList  where
import ColaTAD hiding (pop, top)
import System.Win32 (xBUTTON1)

instance Cola [] where
empty :: p -> [a]
empty xs = []
push :: a -> [a] -> [a]
push x xs = x:xs
top :: [a] -> [a]
top [x]=[x]
top (x:xs)= top xs
isEmpty :: Eq a => [a] -> Bool
isEmpty xs = xs==[]
pop :: [a] -> [a]
pop [x]=[]
pop (x:xs) = x: pop xs
-- TODO complete Stack instance