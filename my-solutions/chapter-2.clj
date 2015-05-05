;;Ex 1
(def second
  (fn [list] (nth list 1)))

;;Ex 2
;;Первый вариант
(def third
  (fn [list] (nth list 2)))

;;Второй вариант
(def third
  (fn [list] (second (rest list))))

;;Ex 3
;;Первый вариант
(def add-squares
  (fn [& numbers] (apply + (map (fn [n] (* n n)) numbers))))

;;Второй вариант
(def add-squares
  (fn [& numbers] (apply + (map * numbers numbers))))

;;Ex 4
(def factorial
  (fn [n] (apply * (range 1 (inc n)))))

;;Ex 5
(take 2 (filter odd? (range 1 8)))
(distinct '(1 1 1 2 11 3 3 4 5 2 2))
(distinct (concat [1 2 3 4] [2 3 5 6]))

;;(_ _ A _ B _ _)
;;Given a list L. Suppose its third and fifth elements are
;;A and B. Produce this list:
;;   ( (A B) (A B) (A B) (A B) )
(def solver
  (fn [l]
    (repeat 4 (list (nth l 2) (nth l 4)))))

;;Return the middlemost 2 elements of an even-element sequence.
(def solver
  (fn [x]
    (interleave x (take (count x) (filter even? (range 1 (last x)))))))

(flatten (interleave [1 3 [5]] [2 4 [6]]))

;;Add the elements of a sequence of elements without using `cons`
(eval (flatten [+ [1 2 3]])) ;; flatten здесь вернет (+ 1 2 3), а eval выполнит это.

;;(1 2 3 4) -> (3 4 1 2)
(flatten (reverse (partition  2 [1 2 3 4])))

;;Is ( () () () )  a list of empty lists?
(def solver (fn [x] (every? empty? x)))
;;Remove all the nil values from a sequence
(remove nil? [1 2 nil 3 4])

;;Ex 6
(def prefix-of?
  (fn [candidate seq]
    (= (take (count candidate) seq) candidate)))

;;Ex 7
(def tails
  (fn [x]
    (map drop
         (range (inc (count x)))
         (repeat (inc (count x)) x))))
