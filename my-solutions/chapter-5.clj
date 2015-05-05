(def make
     (fn [class & args]
       (let [seeded {:__class_symbol__ (:__own_symbol__ class)}
             constructor (:add-instance-values (:__instance_methods__ class))]
         (apply constructor seeded args))))

(def send-to
     (fn [instance message & args]
       (let [class (eval (:__class_symbol__ instance))
             method (message (:__instance_methods__ class))]
         (apply method instance args))))

(def Point
{
  :__own_symbol__ 'Point
  :__instance_methods__
  {
    :add-instance-values (fn [this x y]
                           (assoc this :x x :y y))
    :class :__class_symbol__
    :shift (fn [this xinc yinc]
             (make Point (+ (:x this) xinc)
                         (+ (:y this) yinc)))
    :add (fn [this other]
           (send-to this :shift (:x other)
                                (:y other)))
  }
})

;;Ex 1
(def method-from-message
  (fn [message class]
    (message (:__instance_methods__ class))))

(def class-from-instance
  (fn [instance]
    (eval (:__class_symbol__ instance))))

(def apply-message-to
  (fn [class instance message args]
    (apply (method-from-message message class) instance args)))

(apply-message-to Point a-point :shift [1 3])

(def send-to
  (fn [instance message & args]
    (apply-message-to (class-from-instance instance)
                      instance message args)))

(def make
  (fn [class & args]
    ;;Создается первоначальный мап, к которому потом добавляются нужные инстанс переменные
    (let [seeded {:__class_symbol__ (:__own_symbol__ class)}]
      ;;Добавляем нужные инстанс переменные
      (apply-message-to class seeded :add-instance-values args))))

;;Ex 2
(def Point
{
  :__own_symbol__ 'Point
  :__instance_methods__
  {
    :add-instance-values (fn [this x y]
                           (assoc this :x x :y y))
    :class-name :__class_symbol__
    :class (fn [this]
             (class-from-instance this))
    :shift (fn [this xinc yinc]
             (make Point (+ (:x this) xinc)
                         (+ (:y this) yinc)))
    :add (fn [this other]
           (send-to this :shift (:x other)
                                (:y other)))
  }
})

(def point (make Point 1 2))
(send-to point :class-name)
(send-to point :class)

;;Ex 3
(def point (make Point 1 2))

(def Point
{
  :__own_symbol__ 'Point
  :__instance_methods__
  {
    :add-instance-values (fn [this x y]
                           (assoc this :x x :y y))
    :origin (fn [this] (make Point 0 0))
    :class-name :__class_symbol__
    :class (fn [this] (class-from-instance this))
    :shift (fn [this xinc yinc]
             (make Point (+ (:x this) xinc)
                         (+ (:y this) yinc)))
    :add (fn [this other]
           (send-to this :shift (:x other)
                                (:y other)))
  }
})

(send-to point :origin)

;;Ex 4
(def Holder
{
  :__own_symbol__ 'Holder
  :__instance_methods__
  {
    :add-instance-values (fn [this held]
                           (assoc this :held held))
  }
})

(def apply-message-to
  (fn [class instance message args]
    (if-not (nil? (method-from-message message class))
      (apply (method-from-message message class) instance args)
      (message instance))))

;;Вариант решения из книги
(def apply-message-to
     (fn [class instance message args]
       ;;Вместо if здесь лучше использовать or,
       ;;т.к. запись менее громоздкая и не нужно лишних проверок
       (let [method (or (method-from-message message class)
                        message)]
       (apply method instance args))))

(send-to (make Holder "stuff") :held)
