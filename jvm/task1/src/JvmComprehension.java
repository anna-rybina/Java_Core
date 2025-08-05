public class JvmComprehension {
    // Класс загружается ClassLoader'ами (Bootstrap -> Platform -> Application)
    // Метаданные класса (структура, методы, константы) размещаются в Metaspace

    public static void main(String[] args) {
        // Создается фрейм main в стеке
        int i = 1;                      // 1
        // Примитив i сохраняется в стековом фрейме main

        Object o = new Object();        // 2
        // 1. В куче создается объект Object
        // 2. В стековом фрейме создается ссылка o на этот объект

        Integer ii = 2;                 // 3
        // 1. В куче создается объект Integer (автоупаковка)
        // 2. Для значения 2 может использоваться кэш Integer pool
        // 3. В стеке создается ссылка ii на этот объект

        printAll(o, i, ii);             // 4
        // 1. Создается новый фрейм printAll в стеке
        // 2. Параметры копируются в новый фрейм:
        //    - копия ссылки o (указывает на тот же Object в куче)
        //    - копия примитива i (значение 1)
        //    - копия ссылки ii (указывает на тот же Integer в куче)

        System.out.println("finished"); // 7
        // 1. Строка "finished" берется из String Pool (или создается там)
        // 2. Создается фрейм println в стеке
    }

    private static void printAll(Object o, int i, Integer ii) {
        Integer uselessVar = 700;                   // 5
        // 1. В куче создается объект Integer(700) (вне диапазона Integer pool)
        // 2. В стековом фрейме создается ссылка uselessVar

        System.out.println(o.toString() + i + ii);  // 6
        // 1. Создается фрейм toString() в стеке
        // 2. В куче создаются временные String объекты для конкатенации
        // 3. После выполнения временные объекты становятся unreachable
    }
}

// После завершения методов:
// 1. Стековые фреймы удаляются (main, printAll)
// 2. Объекты Integer(700) и временные String становятся кандидатами на GC
// 3. Когда GC запустится, он освободит память от этих объектов