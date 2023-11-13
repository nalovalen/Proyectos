
import Distribution.Simple.Setup (trueArg)
data Barbol a = Nill | Node a (Barbol a) (Barbol a) 
        deriving (Show)

eqArbol :: (Eq a) => Barbol a -> Barbol a  -> Bool
eqArbol Nill Nill = True
eqArbol (Node a hi hd) Nill = False
eqArbol Nill (Node a hi hd) = False
eqArbol (Node a hi hd) (Node x hi1 hd1) = a==x && eqArbol hi hi1 && eqArbol hd hd1 


insertABB :: (Ord a) => Barbol a -> a -> Barbol a
insertABB Nill x = Node x Nill Nill
insertABB (Node s hi hd) x 
    | s == x = error "No se agrega informacion repetida"
    | s < x = Node s hi (insertABB hd x)
    | s > x = Node s (insertABB hi x) hd

removeABB :: (Ord a) => Barbol a -> a -> Barbol a
removeABB Nill _ = Nill
removeABB (Node s hi hd) x
    | x < s = Node s (removeABB hi x) hd
    | x > s = Node s hi (removeABB hd x)
    | x == s =  if eqArbol hi Nill && eqArbol hd Nill
            then Nill
            else if eqArbol hi Nill
                then hd
                else if eqArbol hd Nill
                    then hi
                    else Node (mayor hi) (removeABB hi (mayor hi)) hd


factorBalance :: Barbol a -> Bool
factorBalance Nill = True
factorBalance (Node _ hi Nill) = False
factorBalance (Node _ Nill hd) = False
factorBalance (Node _ hi hd) = (size hi - size hd) >= -1 && (size hi - size hd) <= 1

size :: Barbol a -> Int
size Nill = 0
size (Node a hi hd) = 1 + maxim (size hi) (size hd)

maxim :: Int -> Int -> Int
maxim a b 
    | a >= b = a
    | otherwise = b

mayor :: Barbol a -> a
mayor (Node s Nill Nill) = s
mayor (Node s hi hd) = mayor hd

mostrarArbol :: (Show a) => Barbol a -> String
mostrarArbol Nill = "Nill"
mostrarArbol (Node x left right) = "(" ++ mostrarArbol left ++ " " ++ show x ++ " " ++ mostrarArbol right ++ ")"

