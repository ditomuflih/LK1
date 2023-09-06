import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class VacuumCleanerEnvironment {
    private int width;
    private int height;
    private Set<Point> dirtySpots = new HashSet<>();
    private Point agentLocation = new Point(0, 0);

    public VacuumCleanerEnvironment(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addDirtySpot(int x, int y) {
        dirtySpots.add(new Point(x, y));
    }

    public boolean isDirty(int x, int y) {
        return dirtySpots.contains(new Point(x, y));
    }

    public Point getAgentLocation() {
        return agentLocation;
    }

    public void moveAgent(int x, int y) {
        agentLocation = new Point(x, y);
    }

    public Set<Point> getDirtySpots() {
        return dirtySpots;
    }

    public static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Point point = (Point) obj;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return 31 * x + y;
        }
    }
}

public class VacuumCleanerSimulator {
    public static void main(String[] args) {
        int width = 5;
        int height = 5;
        VacuumCleanerEnvironment environment = new VacuumCleanerEnvironment(width, height);

        // Tambahkan kotoran ke lingkungan
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            environment.addDirtySpot(x, y);
        }

        int steps = 0;
        while (!environment.getDirtySpots().isEmpty()) {
            VacuumCleanerEnvironment.Point agentLocation = environment.getAgentLocation();
            int x = agentLocation.getX();
            int y = agentLocation.getY();

            // Vacuum membersihkan jika ada kotoran
            if (environment.isDirty(x, y)) {
                environment.getDirtySpots().remove(new VacuumCleanerEnvironment.Point(x, y));
                System.out.println("Membersihkan (" + x + ", " + y + ")");
            }

            // Pindahkan vacuum cleaner secara acak
            int nextX = random.nextInt(width);
            int nextY = random.nextInt(height);
            environment.moveAgent(nextX, nextY);

            steps++;
        }

        System.out.println("Semua kotoran bersih dalam " + steps + " langkah.");
    }
}
