hamming :: (Eq a)=>[a]->[a]->Int
hamming [] [] = 0
hamming [x] [y]
    |x/=y = 1
    |otherwise = 0
hamming (x:xs) (y:ys)
    |x/=y = 1 + hamming xs ys
    |otherwise = hamming xs ys