package com.epam.rd.autocode.set;

import java.util.*;

public class Project {
    private final List<Role> roles;

    public Project(Role... roles) {
        this.roles = Arrays.asList(roles);
    }

    public List<Role> getRoles() {
        return new ArrayList<>(roles);
    }

    public int getConformity(Set<Member> team) {
        List<Entry> projectEntries = new ArrayList<>();
        for (Role role : roles) {
            for (Skill skill : role.getSkills()) {
                projectEntries.add(new Entry(role.getLevel(), skill));
            }
        }
        int originalSize = projectEntries.size();

        List<Entry> teamEntries = new ArrayList<>();
        for (Member member : team) {
            for (Skill skill : member.getSkills()) {
                teamEntries.add(new Entry(member.getLevel(), skill));
            }
        }

        // Compara las entradas del proyecto con las del equipo
        int matchedCount = 0;
        for (Entry projectEntry : projectEntries) {
            if (teamEntries.contains(projectEntry)) {
                matchedCount++;
                teamEntries.remove(projectEntry); // Evita contar múltiples veces
            }
        }

        // Calcula el porcentaje de conformidad
        return (matchedCount * 100) / originalSize;
    }

    private static class Entry {
        private final Level level;
        private final Skill skill;

        public Entry(Level level, Skill skill) {
            this.level = level;
            this.skill = skill;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry entry = (Entry) o;
            return level == entry.level && skill.equals(entry.skill);
        }

        @Override
        public int hashCode() {
            return Objects.hash(level, skill);
        }
    }
}
