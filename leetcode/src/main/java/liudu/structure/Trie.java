package liudu.structure;


//Number 208 前缀树
public class Trie {

  public Trie[] children;
  public boolean isEnd;

  public Trie() {
    children = new Trie[26];
    isEnd = false;
  }

  public void insert(String word) {
    Trie node = this;
    for (int i = 0; i < word.length(); i++) {
      char ch = word.charAt(i);
      int index = ch - 'a';
      if (node.children[index] == null) {
        node.children[index] = new Trie();
      }
      node = node.children[index];
    }

    node.isEnd = true;
  }

  public boolean search(String word) {
    Trie trie = searchPrefix(word);
    return trie != null && trie.isEnd;
  }

  public boolean startsWith(String prefix) {
    Trie trie = searchPrefix(prefix);
    return trie != null;
  }

  private Trie searchPrefix(String prefix) {
    Trie node = this;
    for (int i = 0; i < prefix.length(); i++) {
      char c = prefix.charAt(i);
      int index = c - 'a';

      if (node.children[index] == null) {
        return null;
      }
      node = node.children[index];
    }

    return node;
  }

}
