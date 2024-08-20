package com.epam.rd.autotasks.collections;

import java.util.*;

public class BirthJournalManagementImpl implements BirthJournalManagement {

    private final Map<WeekDay, List<Baby>> journal;
    private boolean isImmutable;

    public BirthJournalManagementImpl() {
        this.journal = new EnumMap<>(WeekDay.class);
        this.isImmutable = false;
    }

    @Override
    public boolean addEntryOfBaby(WeekDay day, Baby baby) {
        if (isImmutable) {
            return false;
        }
        List<Baby> babies = journal.get(day);
        if (babies == null) {
            babies = new ArrayList<>();
            journal.put(day, babies);
        }
        return babies.add(baby);
    }

    @Override
    public void commit() {
        if (!isImmutable) {
            for (Map.Entry<WeekDay, List<Baby>> entry : journal.entrySet()) {
                entry.setValue(Collections.unmodifiableList(entry.getValue()));
            }
            isImmutable = true;
        }
    }

    @Override
    public int amountBabies() {
        int count = 0;
        for (List<Baby> babies : journal.values()) {
            count += babies.size();
        }
        return count;
    }

    @Override
    public List<Baby> findBabyWithHighestWeight(String gender) {
        List<Baby> result = new ArrayList<>();
        Baby highestWeightBaby = null;

        for (List<Baby> babies : journal.values()) {
            for (Baby baby : babies) {
                if (baby.getGender().equalsIgnoreCase(gender)) {
                    if (highestWeightBaby == null || baby.getWeight() > highestWeightBaby.getWeight()) {
                        highestWeightBaby = baby;
                        result.clear();
                        result.add(baby);
                    } else if (baby.getWeight() == highestWeightBaby.getWeight()) {
                        result.add(baby);
                    }
                }
            }
        }

        if (result.size() > 1) {
            // Sorting by name
            Collections.sort(result, new Comparator<Baby>() {
                @Override
                public int compare(Baby b1, Baby b2) {
                    return b1.getName().compareTo(b2.getName());
                }
            });
        }

        return Collections.unmodifiableList(result);
    }

    @Override
    public List<Baby> findBabyWithSmallestHeight(String gender) {
        List<Baby> result = new ArrayList<>();
        Baby smallestHeightBaby = null;

        for (List<Baby> babies : journal.values()) {
            for (Baby baby : babies) {
                if (baby.getGender().equalsIgnoreCase(gender)) {
                    if (smallestHeightBaby == null || baby.getHeight() < smallestHeightBaby.getHeight()) {
                        smallestHeightBaby = baby;
                        result.clear();
                        result.add(baby);
                    } else if (baby.getHeight() == smallestHeightBaby.getHeight()) {
                        result.add(baby);
                    }
                }
            }
        }

        if (result.size() > 1) {
            // Sorting by weight
            Collections.sort(result, new Comparator<Baby>() {
                @Override
                public int compare(Baby b1, Baby b2) {
                    return Double.compare(b1.getWeight(), b2.getWeight());
                }
            });
        }

        return Collections.unmodifiableList(result);
    }

    @Override
    public Set<Baby> findBabiesByBirthTime(String from, String to) {
        Set<Baby> result = new HashSet<>();
        String[] fromTimeParts = from.split(":");
        String[] toTimeParts = to.split(":");

        int fromHour = Integer.parseInt(fromTimeParts[0]);
        int fromMinute = Integer.parseInt(fromTimeParts[1]);
        int toHour = Integer.parseInt(toTimeParts[0]);
        int toMinute = Integer.parseInt(toTimeParts[1]);

        for (List<Baby> babies : journal.values()) {
            for (Baby baby : babies) {
                String birthTime = baby.getTime();
                String[] birthTimeParts = birthTime.split(":");
                int birthHour = Integer.parseInt(birthTimeParts[0]);
                int birthMinute = Integer.parseInt(birthTimeParts[1]);

                if (isTimeInRange(fromHour, fromMinute, toHour, toMinute, birthHour, birthMinute)) {
                    result.add(baby);
                }
            }
        }

        return Collections.unmodifiableSet(result);
    }

    private boolean isTimeInRange(int fromHour, int fromMinute, int toHour, int toMinute, int birthHour, int birthMinute) {
        if (fromHour < toHour || (fromHour == toHour && fromMinute <= toMinute)) {
            return (birthHour > fromHour || (birthHour == fromHour && birthMinute >= fromMinute))
                    && (birthHour < toHour || (birthHour == toHour && birthMinute <= toMinute));
        } else { // Time range crosses midnight
            return (birthHour > fromHour || (birthHour == fromHour && birthMinute >= fromMinute))
                    || (birthHour < toHour || (birthHour == toHour && birthMinute <= toMinute));
        }
    }
}
