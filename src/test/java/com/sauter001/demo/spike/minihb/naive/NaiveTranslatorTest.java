package com.sauter001.demo.spike.minihb.naive;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NaiveTranslatorTest {
    private final NaiveTranslator translator = new NaiveTranslator();

    @Test
    void translateBasic() {
        String sql = "SELECT r FROM Reservation r WHERE r.date = :date";
        String translated = translator.translate(sql);
        assertEquals("SELECT r.* FROM reservation r WHERE r.date = :date", translated);
    }

    @Tag("wall")
    @ParameterizedTest
    @CsvSource({
            "mysql, limit 10, 10",
            "postgresql, fetch first 10 rows only, 10"
    })
    void setMaxResults를_붙인다(String db, String pagingClause, int limit) {
        // db는 번역기에 넘길 자리가 없다. 같은 출력으로 두 행을 다 만족시킬 수 없다
        String sql = translator.translate("SELECT r FROM Reservation r WHERE r.date = :date", limit);
        assertThat(sql).endsWith(pagingClause);
    }

    @Tag("wall")
    @Test
    void r_theme_name붙이기() {
        String sql = "SELECT r FROM Reservation r WHERE r.theme.name = :name";
        String translated = translator.translate(sql);
        assertEquals("SELECT r.* FROM reservation r JOIN theme t ON r.theme_id = t.id WHERE t.name = :name", translated);
    }

    @Tag("wall")
    @Test
    void r_theme_nmae붙이기() {
        String sql = "SELECT r FROM Reservation r WHERE r.theme.nmae = :name";
        String translated = translator.translate(sql);
        assertEquals("SELECT r.* FROM reservation r JOIN theme t ON r.theme_id = t.id WHERE t.name = :name", translated);
    }
}