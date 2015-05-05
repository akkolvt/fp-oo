;;get, assoc, merge, dissoc
(def Point
     (fn [x y]
       {:x x,
        :y y
        :__class_symbol__ 'Point}))

(def x :x)
(def y :y)
(def class-of :__class_symbol__)

(def shift
     (fn [this xinc yinc]
       (Point (+ (x this) xinc)
              (+ (y this) yinc))))

(def Triangle
     (fn [point1 point2 point3]
       {:point1 point1, :point2 point2, :point3 point3
        :__class_symbol__ 'Triangle}))


(def right-triangle (Triangle (Point 0 0)
                              (Point 0 1)
                              (Point 1 0)))

(def equal-right-triangle (Triangle (Point 0 0)
                                    (Point 0 1)
                                    (Point 1 0)))

(def different-triangle (Triangle (Point 0 0)
                                  (Point 0 10)
                                  (Point 10 0)))
;;Ex 1
;;without using shift
(def add
  (fn [this other]
    (assoc this
           :x (+ (x this) (x other)),
           :y (+ (y this) (y other)))))

(add (Point 1 2) (Point 3 4))

;;using shift
(def add
  (fn [this other]
    (shift this (x other) (y other))))

(add (Point 1 2) (Point 3 4))

;;Ex 2
;;Not compatible with Triangle
(def make
  (fn [type arg1 arg2]
    (type arg1 arg2)))

;;Compatible with Triangle
(def make
  (fn [type & args]
    (apply type args)));; apply вызовет переданную в type функцию со всем количеством аргументов

;;Ex 3
(def equal-triangles? =)

;;Ex 5
(def valid-triangle?
  (fn [& points]
    (some = (partition 2 (flatten (repeat 2 points))))))

;;Более правильное решение
(def valid-triangle?
     (fn [& points]
       (and (= 3 (count points));;Проверка того, что всего есть 3 точки
            (= (distinct points) points))));;Вместо искусственного создания списка пар как у меня
