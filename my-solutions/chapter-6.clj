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

