(def make
     (fn [type & args]
       (apply type args)))

(def send-to
     (fn [object message & args]
       (apply (message (:__methods__ object)) object args)))

(def Point
     (fn [x y]
       {:x x,
        :y y
        :__class_symbol__ 'Point
        :__methods__ {
           :class :__class_symbol__
           :shift (fn [this xinc yinc]
                    (make Point (+ (:x this) xinc)
                                (+ (:y this) yinc)))}}))

;;Ex
(def Point
     (fn [x y]
       {:x x,
        :y y
        :__class_symbol__ 'Point
        :__methods__ {
           :class :__class_symbol__
           :x :x
           :y :y
           :shift (fn [this xinc yinc]
                    (make Point (+ (send-to this :x) xinc)
                                (+ (send-to this :y) yinc)))
           :add (fn [this other]
                    (send-to this :shift (send-to other :x) (send-to other :y)))}}))

(def point1 (make Point 1 2))
(def point2 (make Point 3 4))
