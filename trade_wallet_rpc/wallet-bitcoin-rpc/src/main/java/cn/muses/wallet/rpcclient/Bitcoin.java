package cn.muses.wallet.rpcclient;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface Bitcoin {
    void addNode(String var1, AddNoteCmd var2) throws BitcoinException;

    String createRawTransaction(List<TxInput> var1, List<TxOutput> var2) throws BitcoinException;

    RawTransaction decodeRawTransaction(String var1) throws BitcoinException;

    String dumpPrivKey(String var1) throws BitcoinException;

    String getAccount(String var1) throws BitcoinException;

    String getAccountAddress(String var1) throws BitcoinException;

    List<String> getAddressesByAccount(String var1) throws BitcoinException;

    double getBalance() throws BitcoinException;

    double getBalance(String var1) throws BitcoinException;

    double getBalance(String var1, int var2) throws BitcoinException;

    Block getBlock(String var1) throws BitcoinException;

    int getBlockCount() throws BitcoinException;

    String getBlockHash(int var1) throws BitcoinException;

    int getConnectionCount() throws BitcoinException;

    double getDifficulty() throws BitcoinException;

    boolean getGenerate() throws BitcoinException;

    double getHashesPerSec() throws BitcoinException;

    Info getInfo() throws BitcoinException;

    MiningInfo getMiningInfo() throws BitcoinException;

    String getNewAddress() throws BitcoinException;

    String getNewAddress(String var1) throws BitcoinException;

    PeerInfo getPeerInfo() throws BitcoinException;

    String getRawTransactionHex(String var1) throws BitcoinException;

    RawTransaction getRawTransaction(String var1) throws BitcoinException;

    double getReceivedByAccount(String var1) throws BitcoinException;

    double getReceivedByAccount(String var1, int var2) throws BitcoinException;

    double getReceivedByAddress(String var1) throws BitcoinException;

    double getReceivedByAddress(String var1, int var2) throws BitcoinException;

    RawTransaction getTransaction(String var1) throws BitcoinException;

    TxOutSetInfo getTxOutSetInfo() throws BitcoinException;

    Work getWork() throws BitcoinException;

    void importPrivKey(String var1) throws BitcoinException;

    void importPrivKey(String var1, String var2) throws BitcoinException;

    void importPrivKey(String var1, String var2, boolean var3) throws BitcoinException;

    Map<String, Number> listAccounts() throws BitcoinException;

    Map<String, Number> listAccounts(int var1) throws BitcoinException;

    List<ReceivedAddress> listReceivedByAccount() throws BitcoinException;

    List<ReceivedAddress> listReceivedByAccount(int var1) throws BitcoinException;

    List<ReceivedAddress> listReceivedByAccount(int var1, boolean var2) throws BitcoinException;

    List<ReceivedAddress> listReceivedByAddress() throws BitcoinException;

    List<ReceivedAddress> listReceivedByAddress(int var1) throws BitcoinException;

    List<ReceivedAddress> listReceivedByAddress(int var1, boolean var2) throws BitcoinException;

    TransactionsSinceBlock listSinceBlock() throws BitcoinException;

    TransactionsSinceBlock listSinceBlock(String var1) throws BitcoinException;

    TransactionsSinceBlock listSinceBlock(String var1, int var2) throws BitcoinException;

    List<Transaction> listTransactions() throws BitcoinException;

    List<Transaction> listTransactions(String var1) throws BitcoinException;

    List<Transaction> listTransactions(String var1, int var2) throws BitcoinException;

    List<Transaction> listTransactions(String var1, int var2, int var3) throws BitcoinException;

    List<Unspent> listUnspent() throws BitcoinException;

    List<Unspent> listUnspent(int var1) throws BitcoinException;

    List<Unspent> listUnspent(int var1, int var2) throws BitcoinException;

    List<Unspent> listUnspent(int var1, int var2, String... var3) throws BitcoinException;

    String sendFrom(String var1, String var2, double var3) throws BitcoinException;

    String sendFrom(String var1, String var2, double var3, int var5) throws BitcoinException;

    String sendFrom(String var1, String var2, double var3, int var5, String var6) throws BitcoinException;

    String sendFrom(String var1, String var2, double var3, int var5, String var6, String var7) throws BitcoinException;

    String sendMany(String var1, List<TxOutput> var2) throws BitcoinException;

    String sendMany(String var1, List<TxOutput> var2, int var3) throws BitcoinException;

    String sendMany(String var1, List<TxOutput> var2, int var3, String var4) throws BitcoinException;

    String sendRawTransaction(String var1) throws BitcoinException;

    String sendToAddress(String var1, double var2) throws BitcoinException;

    String sendToAddress(String var1, double var2, String var4) throws BitcoinException;

    Boolean setTxFee(double var1) throws BitcoinException;

    String sendToAddress(String var1, double var2, String var4, String var5) throws BitcoinException;

    String signMessage(String var1, String var2) throws BitcoinException;

    String signRawTransaction(String var1) throws BitcoinException;

    void stop() throws BitcoinException;

    AddressValidationResult validateAddress(String var1) throws BitcoinException;

    boolean verifyMessage(String var1, String var2, String var3) throws BitcoinException;

    interface AddressValidationResult {
        boolean isValid();

        String address();

        boolean isMine();

        boolean isScript();

        String pubKey();

        boolean isCompressed();

        String account();
    }

    interface Unspent extends TxInput, TxOutput {
        String txid();

        int vout();

        String address();

        String account();

        String scriptPubKey();

        BigDecimal amount();

        int confirmations();
    }

    interface TransactionsSinceBlock {
        List<Transaction> transactions();

        String lastBlock();
    }

    interface Transaction {
        String account();

        String address();

        String category();

        double amount();

        double fee();

        int confirmations();

        String blockHash();

        int blockIndex();

        Date blockTime();

        String txId();

        Date time();

        Date timeReceived();

        String comment();

        String commentTo();

        RawTransaction raw();
    }

    interface ReceivedAddress {
        String address();

        String account();

        double amount();

        int confirmations();
    }

    interface Work {
        String midstate();

        String data();

        String hash1();

        String target();
    }

    interface TxOutSetInfo {
        int height();

        String bestBlock();

        int transactions();

        int txOuts();

        int bytesSerialized();

        String hashSerialized();

        double totalAmount();
    }

    interface RawTransaction {
        String hex();

        String txId();

        int version();

        long lockTime();

        List<In> vIn();

        List<Out> vOut();

        String blockHash();

        int confirmations();

        Date time();

        Date blocktime();

        interface Out {
            double value();

            int n();

            ScriptPubKey scriptPubKey();

            TxInput toInput();

            RawTransaction transaction();

            interface ScriptPubKey {
                String asm();

                String hex();

                int reqSigs();

                String type();

                List<String> addresses();
            }
        }

        interface In extends TxInput {
            Map<String, Object> scriptSig();

            long sequence();

            RawTransaction getTransaction();

            Out getTransactionOutput();
        }
    }

    interface PeerInfo {
        String addr();

        String services();

        int lastsend();

        int lastrecv();

        int bytessent();

        int bytesrecv();

        int blocksrequested();

        Date conntime();

        int version();

        String subver();

        boolean inbound();

        int startingheight();

        int banscore();
    }

    interface MiningInfo {
        int blocks();

        int currentblocksize();

        int currentblocktx();

        double difficulty();

        String errors();

        int genproclimit();

        double networkhashps();

        int pooledtx();

        boolean testnet();

        String chain();

        boolean generate();
    }

    interface Info {
        int version();

        int protocolversion();

        int walletversion();

        double balance();

        int blocks();

        int timeoffset();

        int connections();

        String proxy();

        double difficulty();

        boolean testnet();

        int keypoololdest();

        int keypoolsize();

        int unlocked_until();

        double paytxfee();

        double relayfee();

        String errors();
    }

    interface Block {
        String hash();

        int confirmations();

        int size();

        int height();

        int version();

        String merkleRoot();

        List<String> tx();

        Date time();

        long nonce();

        String bits();

        double difficulty();

        String previousHash();

        String nextHash();

        Block previous() throws BitcoinException;

        Block next() throws BitcoinException;
    }

    class BasicTxOutput implements TxOutput {
        public String address;
        public BigDecimal amount;

        public BasicTxOutput(String address, BigDecimal amount) {
            this.address = address;
            this.amount = amount;
        }

        public String address() {
            return this.address;
        }

        public BigDecimal amount() {
            return this.amount;
        }
    }

    interface TxOutput {
        String address();

        BigDecimal amount();
    }

    class BasicTxInput implements TxInput {
        public String txid;
        public int vout;

        public BasicTxInput(String txid, int vout) {
            this.txid = txid;
            this.vout = vout;
        }

        public String txid() {
            return this.txid;
        }

        public int vout() {
            return this.vout;
        }
    }

    interface TxInput {
        String txid();

        int vout();
    }

    enum AddNoteCmd {
        add,
        remove,
        onetry;

        AddNoteCmd() {
        }
    }
}
