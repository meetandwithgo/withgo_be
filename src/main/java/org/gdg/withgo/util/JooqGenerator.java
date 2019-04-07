package org.gdg.withgo.util;

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;

public class JooqGenerator {

    public static void main(String[] args) throws Exception {
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        Configuration configuration = new Configuration()
                .withJdbc(new Jdbc()
                        .withDriver("org.postgresql.Driver")
                        .withUrl("jdbc:postgresql:postgres")
                        .withUser(user)
                        .withPassword(password))
                .withGenerator(new Generator()
                        .withDatabase(new Database()
                                .withName("org.jooq.meta.postgres.PostgresDatabase")
                                .withIncludes(".*")
                                .withExcludes("")
                                .withInputSchema("public"))
                        .withTarget(new Target()
                                .withPackageName("org.jooq.codegen.maven.example")
                                .withDirectory("target/generated-sources/jooq")));

        GenerationTool.generate(configuration);
    }
}
