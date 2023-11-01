package com.ohgiraffers.section02.annotation.subsection02.qualifier;

import com.ohgiraffers.section02.common.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("pokemonServiceQualifier")
public class PokemonService {

    /* @Qualifier 어노테이션은 여러 개의 빈 객체 중에서 특정 빈 객체를 이름으로 지정하는 어노테이션이다.
     * @Primary 어노테이션과 @Qualifier 어노테이션이 함께 쓰인 경우에는 @Qualifier 어노테이션이 우선한다. */

    /* 1. 필드 주입 방식 */
//    @Autowired
//    @Qualifier("pikachu")
    private Pokemon pokemon;

    /* 2. 생성자 주입 방식 */
    @Autowired
    public PokemonService(@Qualifier("pikachu") Pokemon pokemon) { this.pokemon = pokemon; }

    public void pokemonAttack() {
        pokemon.attack();
    }
}