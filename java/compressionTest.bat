for /l %%x in (1,1,1) do (
    java -cp "target\classes;lib/*" kanzi.app.Kanzi -c -i data/dataset.json -o output/comp.knz -f -t BWT -b 1m -e Huffman -j 1
    java -cp "target\classes;lib/*" kanzi.app.Kanzi -d -i output/comp.knz -o output/decomp.json -f -j 1
    java -cp "target\classes;lib/*" kanzi.app.Kanzi -c -i data/dataset.json -o output/comp.knz -f -t BWT -b 2m -e Huffman -j 1
    java -cp "target\classes;lib/*" kanzi.app.Kanzi -d -i output/comp.knz -o output/decomp.json -f -j 1
    java -cp "target\classes;lib/*" kanzi.app.Kanzi -c -i data/dataset.json -o output/comp.knz -f -t BWT -b 4m -e Huffman -j 1
    java -cp "target\classes;lib/*" kanzi.app.Kanzi -d -i output/comp.knz -o output/decomp.json -f -j 1
    java -cp "target\classes;lib/*" kanzi.app.Kanzi -c -i data/dataset.json -o output/comp.knz -f -t BWT -b 8m -e Huffman -j 1
    java -cp "target\classes;lib/*" kanzi.app.Kanzi -d -i output/comp.knz -o output/decomp.json -f -j 1
    java -cp "target\classes;lib/*" kanzi.app.Kanzi -c -i data/dataset.json -o output/comp.knz -f -t BWT -b 16m -e Huffman -j 1
    java -cp "target\classes;lib/*" kanzi.app.Kanzi -d -i output/comp.knz -o output/decomp.json -f -j 1
    java -cp "target\classes;lib/*" kanzi.app.Kanzi -c -i data/dataset.json -o output/comp.knz -f -t LZ -b 1m -e Huffman -j 1
    java -cp "target\classes;lib/*" kanzi.app.Kanzi -d -i output/comp.knz -o output/decomp.json -f -j 1
    java -cp "target\classes;lib/*" kanzi.app.Kanzi -c -i data/dataset.json -o output/comp.knz -f -t LZ -b 2m -e Huffman -j 1
    java -cp "target\classes;lib/*" kanzi.app.Kanzi -d -i output/comp.knz -o output/decomp.json -f -j 1
    java -cp "target\classes;lib/*" kanzi.app.Kanzi -c -i data/dataset.json -o output/comp.knz -f -t LZ -b 4m -e Huffman -j 1
    java -cp "target\classes;lib/*" kanzi.app.Kanzi -d -i output/comp.knz -o output/decomp.json -f -j 1
    java -cp "target\classes;lib/*" kanzi.app.Kanzi -c -i data/dataset.json -o output/comp.knz -f -t LZ -b 8m -e Huffman -j 1
    java -cp "target\classes;lib/*" kanzi.app.Kanzi -d -i output/comp.knz -o output/decomp.json -f -j 1
    java -cp "target\classes;lib/*" kanzi.app.Kanzi -c -i data/dataset.json -o output/comp.knz -f -t LZ -b 16m -e Huffman -j 1
    java -cp "target\classes;lib/*" kanzi.app.Kanzi -d -i output/comp.knz -o output/decomp.json -f -j 1
)