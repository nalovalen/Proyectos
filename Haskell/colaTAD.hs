{-|
Module      : StackADT
Description : Provides the class for the Stack ADT
Copyright   : Estructuras de Datos y Alg. UNRC
License     : GPL-3
We use haddock style of comments
-}

module ColaTAD where

-- | A class for stacks:
-- s is a type constructor, for example: lists.
class Cola s where 
    -- | empty Stack
    empty :: s a -> s a
    -- | push an element into the Stack
    push :: a -> s a -> s a
    -- / pop an element into Stack
    pop :: s a -> s a
    -- / isEmpty return true or false if stack is empty
    isEmpty :: s a -> Bool
    -- / top returns the top element of the stack
    top :: s a -> a
-- TODO complete Stack class