elemMay :: (Ord a)=>[a]->a
elemMay [] = error "secuencia vacia"
elemMay [a] = a
elemMay (x:y:xs)
    |x>=y = elemMay (x:xs)
    |otherwise = elemMay (y:xs)

elemMin :: (Ord a)=>[a]->a
elemMin [] = error "secuencia vacia"
elemMin [a] = a
elemMin (x:y:xs)
    |x>=y = elemMin (y:xs)
    |otherwise = elemMin (x:xs)
