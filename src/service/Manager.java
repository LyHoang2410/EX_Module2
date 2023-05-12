package service;

public interface Manager<E> {
    E create();
    E update();
    E delete();
    E getByCode();
    void displayAll();
}
