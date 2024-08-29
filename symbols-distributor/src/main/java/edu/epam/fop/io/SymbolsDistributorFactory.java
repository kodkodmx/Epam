package edu.epam.fop.io;

public class SymbolsDistributorFactory {
    public SymbolsDistributor getInstance() {
        return new SymbolsDistributorImpl();
    }
}
