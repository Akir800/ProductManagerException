public class Manager {

    private final Repository repository;

    public Manager(Repository repository) {
        this.repository = repository;
    }

    public void add(Product product) {
        repository.save(product);
    }

    public Product[] searchBy(String text) {
        Product[] result = new Product[0]; // тут будем хранить подошедшие запросу продукты
        for (Product product : repository.findAll()) {
            if (matches(product, text)) {
                // "добавляем в конец" массива result продукт product
                //создаём новый массив, вычисляем длинну + 1
                Product[] tmp = new Product[result.length + 1];
                //копируем: откуда копируем, с какой позиции начинаем, куда копируем, с какой позиции копируем, сколько копируем
                System.arraycopy(result, 0, tmp, 0, result.length);
                //последняя ячейка
                tmp[tmp.length - 1] = product;
                result = tmp;
            }
        }
        return result;
    }

    // метод определения соответствия товара product запросу search
    public boolean matches(Product product, String search) {
        if (product.getName().contains(search)) {
            return true;
        } else {
            return false;
        }
        // или в одну строку:
        // return product.getName().contains(search);
    }

}