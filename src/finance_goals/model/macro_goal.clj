(ns finance-goals.model.macro-goal
  (:require [schema.core :as s]))

(s/defschema macro-goal
             {:id              s/Int
              :name            (s/constrained s/Str #(<= (count %) 120) 'name-max-120)
              :step            (s/constrained s/Int #(<= 0 % 9) 'step-0-to-9)
              :current-date-goal s/Str
              :goal-date       s/Str
              :goal-value      (s/constrained s/Num pos? 'goal-value-positive)
              :created-at      s/Str
              :updated-at      s/Str})
