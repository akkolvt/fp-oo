;;Ex 1
(def factorial
  (fn [n]
    (if (= 0 n)
      1
      (* (factorial (dec n)) n))))

;;Ex 2
;;Вариант с передачей списка. Можно так только если задавать дефолтное значение fact-list,
;;иначе придется передавать лишний параметр.
(def factorial
  (fn [n fact-list]
    (if (= 0 n)
      (apply * fact-list)
      (factorial (dec n) (cons n fact-list)))))

;;Вариант с передачей произведения
(def factorial
  (fn [n] (dec-factorial n 1)))

(def dec-factorial
  (fn [n so-far]
    (if (or (= 0 n)
            (= 1 n))
      so-far
      (dec-factorial (dec n) (* n so-far)))))

;;Ex 3
(def recursive-sum
  (fn [l so-far]
    (if (empty? l)
      so-far
      (recursive-sum (rest l) (+ so-far (first l))))))

(prn (recursive-sum [1 2 3 4] 0))

;;Ex 4
;;Первый вариант
(def recursive-product
  (fn [l so-far]
    (if (empty? l)
      so-far
      (recursive-product (rest l) (* so-far (first l))))))

(prn (recursive-product [1 2 3 4] 1))

;;После вынесения общей части
(def recursive-operation
  (fn [op l so-far]
    (if (empty? l)
      so-far
      (recursive-operation op (rest l) (apply op (list so-far (first l)))))))

(prn (recursive-operation + [1 2 3 4] 0))
(prn (recursive-operation * [1 2 3 4] 1))

;;Ex 5

