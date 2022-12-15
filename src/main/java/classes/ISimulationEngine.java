package classes;

public interface ISimulationEngine {
    // In case we decide to change order of events in single day
    // we should implement run method in way fitting new requirements
    // This method specifies chain of actions that hold the simulation
    void run();
}
