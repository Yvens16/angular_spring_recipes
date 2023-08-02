| Keyword            | Example                                                 | Generated JPQL                                                |   |   |
|--------------------|---------------------------------------------------------|---------------------------------------------------------------|---|---|
| Is, Equals         | findByUsername, findByUsernameIs, findByUsernameEquals  | ... where e.username=?1                                       |   |   |
| And                | findByUsernameAndRegistrationDate                       | . . . where e.username = ?1 and e.registrationdate= ?2        |   |   |
| Or                 | findByUsernameOrRegistrationDate                        | . . .  where e.username = ?1 or e.registrationdate = ?2       |   |   |
| LessThan           | findByRegistrationDateLessThan                          | . . .  where e.registrationdate < ?1                          |   |   |
| LessThanEqual      | findByRegistrationDateLessThanEqual                     | . . .  where e.registrationdate <= ?1                         |   |   |
| GreaterThan        | findByRegistrationDateGreaterThan                       | . . .  where e.registrationdate > ?1                          |   |   |
| GreaterThanEqual   | findByRegistrationDateGreaterThanEqual                  | . . .  where e.registrationdate >= ?1                         |   |   |
| Between            | findByRegistrationDateBetween                           | . . .  where e.registrationdate between ?1 and ?2             |   |   |
| OrderBy            | findByRegistrationDateOrderByUsernameDesc               | . . .  where e.registrationdate = ?1 order by e.username desc |   |   |
| Like               | findByUsernameLike                                      | . . .  where e.username like ?1                               |   |   |
| NotLike            | findByUsernameNotLike                                   | . . .  where e.username not like ?1                           |   |   |
| Before             | findByRegistrationDateBefore                            | . . .  where e.registrationdate < ?1                          |   |   |
| After              | findByRegistrationDateAfter                             | . . .  where e.registrationdate > ?1                          |   |   |
| Null, IsNull       | findByRegistrationDate(Is)Null                          | . . .  where e.registrationdate is null                       |   |   |
| NotNull, IsNotNull | findByRegistrationDate(Is)NotNull                       | . . .  where e.registrationdate is not null                   |   |   |
| Not                | findByUsernameNot                                       | . . .  where e.username <> ? 1                                |   |   |